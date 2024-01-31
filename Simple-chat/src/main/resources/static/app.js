const socket = new SockJS('/chat');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    // Handle connection
});

function sendMessage() {
    const message = // construct your message
        stompClient.send("/app/chat", {}, JSON.stringify(message));
}

stompClient.subscribe('/topic/messages', function (response) {
    // Handle received messages
});
