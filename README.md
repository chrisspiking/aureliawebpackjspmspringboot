# aureliawebpackjspmspringboot
Simple project to try getting a Spring Boot server to serve / work with both a webpack aurelia project as well as a jspm version

Installation: 

Within src/main/resources/static/aureliajspm, run the following:

jspm install
typings install
npm install --save-dev @types/node
npm install --save-dev @types/socket.io
npm install --save-dev @types/socket.io-client
npm install --save-dev @types/stomp
npm install --save-dev @types/stomp-websocket
npm install --save-dev @types/SockJS
npm install --save-dev @types/sockjs-client
npm install --save-dev @types/stompjs

Within src/main/resources/static/aureliawebpack, run the following:

npm install
