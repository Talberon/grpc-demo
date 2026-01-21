mkdir webchatpb
protoc --go_out=./webchatpb --go_opt=paths=source_relative \
    --go-grpc_out=./webchatpb --go-grpc_opt=paths=source_relative \
--proto_path=../protobuf webchat-service.proto