# aureliawebpackjspmspringboot
Simple project to try getting a Spring Boot server to serve / work with both a webpack aurelia project as well as a jspm version

Installation: 
 
You will need node 8.11+ and npm version 5.6.0 installed.

You will need jspm and typings installed.

```
npm install jspm -g
npm install typings -g
```

Within ```src/main/resources/static/aureliajspm```, run the following:

```
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
```

Within ```src/main/resources/static/aureliawebpack```, run the following:

```
npm install
```

To run, execute the Application.class through Java to spin up Spring Boot.

Please note that currently the aureliawebpack side of this application is not working.

There is a chance that systemjs will have a problem because of a version variable bug in registry-endpoint. If this happens, you'll need to make the change seen here in your npm_modules repo for registry-endpoint:

https://github.com/jspm/registry-endpoint/pull/19/commits/7c7a281037e5591d7e8b94d2719fe9e05a3139fc
