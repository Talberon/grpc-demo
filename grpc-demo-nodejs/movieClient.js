import grpc from '@grpc/grpc-js';
import protoLoader from '@grpc/proto-loader';
import path from 'path';
import { fileURLToPath } from 'url';

// Recreate __dirname in ESM
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const PROTO_PATH = path.resolve(__dirname, '../protobuf/movie-service.proto');

const packageDefinition = protoLoader.loadSync(PROTO_PATH, {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true
});

const proto = grpc.loadPackageDefinition(packageDefinition);
const movieProto = proto.moviecatalog;

function main() {
  const client = new movieProto.MovieCatalog(
    'localhost:8080',
    grpc.credentials.createInsecure()
  );

  const terminator2 = {
    name: 'Terminator 2',
    price: 20.95,
    inStock: true
  };

  console.log('Saving movie:', terminator2);

  client.SaveNewMovie(terminator2, (err, response) => {
    if (err) {
      console.error('SaveNewMovie error:', err);
      return;
    }
    console.log('Response:', response);
  });

  const fetchRequest = {
    name: 'The Lion King'
  };

  console.log('Fetching movie:', fetchRequest);

  client.FetchExistingMovie(fetchRequest, (err, response) => {
    if (err) {
      console.error('FetchExistingMovie error:', err);
      return;
    }
    console.log('Response:', response);
  });
}

main();