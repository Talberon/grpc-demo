import React, { useState, useEffect } from "react";
import { render, Box, Text, useInput } from "ink";
import grpc from "@grpc/grpc-js";
import protoLoader from "@grpc/proto-loader";
import path from "path";
import { fileURLToPath } from "url";
import { promisify } from "util";

// Recreate __dirname in ESM
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const PROTO_PATH = path.resolve(__dirname, "../protobuf/webchat-service.proto");

// Load protobuf definition
const packageDefinition = protoLoader.loadSync(PROTO_PATH, {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true,
});

// Load gRPC package
const proto = grpc.loadPackageDefinition(packageDefinition);
const chatClientProto = proto.webchat;

const chatRoom = { chatRoomId: "My Cool Room For Cool People" };

const client = new chatClientProto.WebChat(
  "localhost:9090",
  grpc.credentials.createInsecure(),
);
const sendMessage = promisify(client.sendMessage.bind(client));
const chatStream = client.joinChatRoom(chatRoom);

let nextId = 1;
const newMessage = (text) => ({ id: nextId++, text });

function ChatApp() {
  const [messages, setMessages] = useState([
    newMessage("Connected to web chat!"),
  ]);
  const [input, setInput] = useState("");
  const [error, setError] = useState(null);

  useEffect(() => {
    chatStream.on("data", (feature) => {
      const timestamp = new Date(
        parseInt(feature.timeGeneratedEpochMillis),
      ).toLocaleDateString("en-US", {
        hour: "2-digit",
        minute: "2-digit",
        second: "2-digit",
      });
      setMessages((prev) => [
        ...prev,
        {
          id: Date.now(),
          text: `[RECEIVED]: [${timestamp}] (${feature.clientLanguage}) ${feature.nickname} says: ${feature.message}`,
        },
      ]);
    });

    chatStream.on("end", () => {
      setMessages((prev) => [
        ...prev,
        { id: Date.now(), text: "The server has finished sending" },
      ]);
    });

    chatStream.on("error", (err) => {
      setError(`Stream error: ${err.message}`);
    });
  }, []);

  useInput((char, key) => {
    if (key.return) {
      const text = input.trim();
      if (!text) return;
      setInput("");
      const message = {
        chatRoom,
        message: text,
        timeGeneratedEpochMillis: Date.now(),
        nickname: "Deborah",
        clientLanguage: "Javascript",
      };
      sendMessage(message)
        .then((receipt) => {
          setMessages((prev) => [
            ...prev,
            {
              id: Date.now(),
              text: `[SENT]: ${JSON.stringify(message)} (receipt: ${JSON.stringify(receipt)})`,
            },
          ]);
        })
        .catch((err) => {
          setError(`RPC error: ${err.message}`);
        });
    } else if (key.backspace || key.delete) {
      setInput((prev) => prev.slice(0, -1));
    } else if (!key.ctrl && !key.meta) {
      setInput((prev) => prev + char);
    }
  });

  return (
    <Box flexDirection="column">
      <Box borderStyle={"single"} flexDirection="column">
        {messages
          .filter((msg) => msg.text.startsWith("[RECEIVED"))
          .map((msg) => (
            <Text key={msg.id}>{msg.text}</Text>
          ))}
        {error && <Text color="red">{error}</Text>}
      </Box>
      <Box marginTop={2}>
        {messages
          .filter((msg) => msg.text.startsWith("[SENT"))
          .map((msg) => (
            <Text key={msg.id} color="green">
              {msg.text}
            </Text>
          ))
          .at(-1)}
      </Box>
      <Box
        borderStyle={"single"}
        borderTop={true}
        borderLeft={false}
        borderRight={false}
        borderBottom={false}
      >
        <Text>
          Send a message: <Text color="green">{input}</Text>
        </Text>
      </Box>
    </Box>
  );
}

render(<ChatApp />);
