using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Channels;
using System.Threading.Tasks;
using Webchat;

namespace GrpcDemoDotnet.WebChat.Server;

public class ChatRoomHub
{
    private readonly ConcurrentDictionary<string, Room> _rooms = [];

    public ChannelReader<ChatMessage> Subscribe(string roomId)
    {
        Room room = _rooms.GetOrAdd(roomId, _ => new Room());
        return room.Subscribe();
    }

    public ValueTask PublishAsync(string roomId, ChatMessage message, CancellationToken token)
    {
        Room room = _rooms.GetOrAdd(roomId, _ => new Room());
        return room.PublishAsync(message, token);
    }

    private sealed class Room
    {
        private readonly Lock _gate = new();
        private readonly List<Channel<ChatMessage>> _subscribers = [];

        public ChannelReader<ChatMessage> Subscribe()
        {
            var channel = Channel.CreateUnbounded<ChatMessage>();
            lock (_gate)
            {
                _subscribers.Add(channel);
            }

            return channel.Reader;
        }

        public ValueTask PublishAsync(ChatMessage message, CancellationToken token)
        {
            Channel<ChatMessage>[] subscribersSnapshot;
            lock (_gate)
            {
                subscribersSnapshot = _subscribers.ToArray();
            }

            foreach (Channel<ChatMessage> subscriber in subscribersSnapshot)
            {
                subscriber.Writer.TryWrite(message);
            }

            return ValueTask.CompletedTask;
        }
    }
}