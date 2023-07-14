const PROTO_PATH = '../protobuf/movie-service.proto';
const grpc = require('@grpc/grpc-js');
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
const movie_proto = grpc.loadPackageDefinition(packageDefinition).moviecatalog;
// The protoDescriptor object has the full package hierarchy
// const routeguide = protoDescriptor.routeguide;

function main() {
    let client = new movie_proto.MovieCatalog('localhost:8080', grpc.credentials.createInsecure());

    let terminator2 = {
        name: "Terminator 2",
        price: 20.95,
        inStock: true
    };

    console.log("Saving movie: ", terminator2);

    client.SaveNewMovie(terminator2, function (err, response) {
        if (response) console.log("Response: ", response);
        if (err) console.error(err);
    });

    let fetchRequest = {
        name: "The Lion King"
    }

    console.log("Fetching movie: ", fetchRequest);

    client.FetchExistingMovie(fetchRequest, function (err, response) {
        if (response) console.log("Response: ", response);
        if (err) console.error(err);
    });
}

main();