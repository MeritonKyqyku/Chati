const connection = new WebSocket('ws://localhost:81'),
    box=document.getElementById("box"),
    msg=document.getElementById("msg");

connection.addEventListener('open',()=>{
    console.log("connected");
}); 

connection.addEventListener('message',e=>{
    let p =document.createElement('p');

    p.textContent=e.data;
    box.appendChild(p);
});

function send(data){
    if(connection.readyState ===WebSocket.OPEN){
        connection.send(data);

    } else {
        throw 'not connected';
    }

}

msg.addEventListener('keydown',e=>{
    let kc= e.which || e.keyCode;

    if(kc ===13){
        send(msg.value);
        msg.value='';
    }
});