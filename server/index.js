var app = require("express")();
var server = require("http").Server(app);
var io = require("socket.io")(server);

server.listen(8081, function()
    {
        console.log("Server is now running...\n");
    });

io.on('connection', function(socket)
    {
        console.log("Player Connected!\n");

        socket.emit("socketID", {id: socket.id});

        socket.on("disconnect", function()
        {
            console.log("Player Disconnected\n");
        });
});