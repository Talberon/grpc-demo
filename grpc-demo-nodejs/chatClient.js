const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});

const PROTO_PATH = '../protobuf/webchat-service.proto';
const grpc = require('grpc');
const protoLoader = require('@grpc/proto-loader');
// Suggested options for similarity to existing grpc.load behavior
const packageDefinition = protoLoader.loadSync(
    PROTO_PATH,
    {
        keepCase: true,
        longs: String,
        enums: String,
        defaults: true,
        oneofs: true
    });
const chatClientProto = grpc.loadPackageDefinition(packageDefinition).webchat;

function main() {
    //Set up the client
    let client = new chatClientProto.WebChat('localhost:9090', grpc.credentials.createInsecure());

    let chatRoom = {
        chatRoomId: "My Cool Room For Cool People"
    };

    connectToChatRoom(client, chatRoom);

    //Prompt the user for input on the command line for sending messages
    var recursiveQuestions = function () {
        readline.question('Send a message: ', text => {
            sendMessage(chatRoom, text, client);
            recursiveQuestions();
        });
    }

    recursiveQuestions();
}

main();

function sendMessage(chatRoom, text, client) {
    let message = {
        "chatRoom": chatRoom,
        "message": text,
        "timeGeneratedEpochMillis": Date.now(),
        "nickname": "Deborah",
        "clientLanguage": "Javascript"
    };
    client.sendMessage(message, function (receipt) {
        console.log(`Message ${JSON.stringify(message)} was sent successfully?: ${receipt}`);
    });
}

function connectToChatRoom(client, chatRoom) {
    let chatStream = client.joinChatRoom(chatRoom);

    console.log("Connected to web chat!", chatRoom);

    chatStream.on('data', function (feature) {
        console.log("Received message: ", feature);
    });
    chatStream.on('end', function () {
        console.log("The server has finished sending");
    });
    chatStream.on('error', function (e) {
        console.log("An error has occurred and the stream has been closed");
    });
    chatStream.on('status', function (status) {
        console.log("process status");
    });
}
