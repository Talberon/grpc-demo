import readline from 'readline';
import grpc from '@grpc/grpc-js';
import protoLoader from '@grpc/proto-loader';
import path from 'path';
import { fileURLToPath } from 'url';
import { promisify } from 'util';

// Recreate __dirname in ESM
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const PROTO_PATH = path.resolve(__dirname, '../protobuf/webchat-service.proto');

// readline interface
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

// Load protobuf definition
const packageDefinition = protoLoader.loadSync(PROTO_PATH, {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true
});

// Load gRPC package
const proto = grpc.loadPackageDefinition(packageDefinition);
const chatClientProto = proto.webchat;

async function main() {
  const client = new chatClientProto.WebChat(
    'localhost:9090',
    grpc.credentials.createInsecure()
  );
  const sendMessage = promisify(client.sendMessage.bind(client));

  const chatRoom = {
    chatRoomId: 'My Cool Room For Cool People'
  };

  await connectToChatRoom(client, chatRoom);
  askQuestions(sendMessage, chatRoom);
}

function askQuestions(sendMessage, chatRoom) {
  rl.question('Send a message: ', async text => {
    try {
      const message = {
        chatRoom,
        message: text,
        timeGeneratedEpochMillis: Date.now(),
        nickname: 'Deborah',
        clientLanguage: 'Javascript'
      };
      const receipt = await sendMessage(message);
      console.log(`Message ${JSON.stringify(message)} was sent successfully?: ${receipt}`);
    } catch (err) {
      console.error('RPC error:', err);
    }
    askQuestions(sendMessage, chatRoom);
  });
}

async function connectToChatRoom(client, chatRoom) {
  const chatStream = client.joinChatRoom(chatRoom);

  console.log('Connected to web chat!', chatRoom);

  chatStream.on('data', feature => {
    console.log(`[${new Date(parseInt(feature.timeGeneratedEpochMillis)).toISOString()}] (${feature.clientLanguage}) ${feature.nickname} says: ${feature.message}`);
  });

  chatStream.on('end', () => {
    console.log('The server has finished sending');
    rl.close();
  });

  chatStream.on('error', err => {
    console.error('Stream error:', err);
  });

  chatStream.on('status', status => {
    console.log('Stream status:', status);
  });
}

main();