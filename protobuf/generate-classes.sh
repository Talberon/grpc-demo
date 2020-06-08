# If you do not have protoc installed, you can download it here: https://github.com/protocolbuffers/protobuf/releases
mkdir -p movie-service-java;
mkdir -p movie-service-dotnet;
mkdir -p movie-service-node;

protoc-3.12.2-win64/bin/protoc.exe --proto_path=. \
--plugin=protoc-gen-grpc-java=./protoc-gen-grpc-java.exe \
--grpc-java_out=movie-service-java \
--java_out=movie-service-java \
--grpc-java_out=../grpc-demo-java/src/main/java \
--java_out=../grpc-demo-java/src/main/java \
--js_out=movie-service-node \
--csharp_out=movie-service-dotnet \
./movie-service.proto && \
echo "Files generated successfully!";