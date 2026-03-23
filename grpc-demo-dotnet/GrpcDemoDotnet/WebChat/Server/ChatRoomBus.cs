using System.Collections.Concurrent;
using System.Threading;
using System.Threading.Channels;
using System.Threading.Tasks;
using Webchat;

namespace GrpcDemoDotnet.WebChat.Server;

public class ChatRoomBus
{
    private readonly ConcurrentDictionary<string, Channel<ChatMessage>> _rooms = [];

    public ChannelReader<ChatMessage> Subscribe(string roomId)
    {
        Channel<ChatMessage> channel = _rooms.GetOrAdd(roomId,
            // Use an unbounded channel so that our channel can grow if more messages are received.
            _ => Channel.CreateUnbounded<ChatMessage>(
                new UnboundedChannelOptions
                {
                    // These properties are used for concurrency's sake; not memory.
                    SingleReader = false,
                    SingleWriter = false
                })
        );

        return channel.Reader;
    }

    public async ValueTask PublishAsync(
        string roomId,
        ChatMessage message,
        CancellationToken cancellationToken = default
    )
    {
        Channel<ChatMessage> channel = _rooms.GetOrAdd(roomId, _ => Channel.CreateUnbounded<ChatMessage>());
        await channel.Writer.WriteAsync(message, cancellationToken);
    }
}