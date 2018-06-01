import {inject} from 'aurelia-framework';
import {EventAggregator} from 'aurelia-event-aggregator';
import {HttpClient, HttpResponseMessage, RequestBuilder} from 'aurelia-http-client';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

export class Person {
    public id: string = "";
    public forename: string = "";
    public surname: string = "";
}

@inject(HttpClient, EventAggregator)
export class PersonService {

    static readonly EVENT_TOPIC_PERSON_LIST_UPDATE: string = 'personListUpdate';

    private stompClient: Stomp.Client;

    constructor(private http: HttpClient, private ea: EventAggregator) {
        this.connectToWebSocket();
    }

    private connectToWebSocket() {
        let socket = new SockJS('/person-demo-websocket');
        this.stompClient = Stomp.over(socket);

        let sc = this.stompClient;
        let ea = this.ea;
        sc.connect({}, function (frame) {
            console.log('WebSocket Connected: ' + frame);
            sc.subscribe('/topic/personListUpdate', function (personListUpdateNotification) {
                ea.publish(PersonService.EVENT_TOPIC_PERSON_LIST_UPDATE);
            });
        });
    }

    public async addPerson(forename: string, surname: string): Promise<Person> {
        let addRequestBuild: RequestBuilder = this.http.createRequest('/addPerson').asPost().withParams({forename: forename, surname: surname});
        let response: HttpResponseMessage = await addRequestBuild.send();
        if(response.isSuccess) {
            return response.content;
        } else {
            return null;
        }
    }

    public async getAllPeople(): Promise<Person[]> {
        let getRequestBuild: RequestBuilder = this.http.createRequest('/getPeople').asGet();
        let response: HttpResponseMessage = await getRequestBuild.send();
        if(response.isSuccess) {
            return response.content;
        } else {
            return null;
        }
    }

    public async getPerson(personId: number): Promise<Person> {
        let getRequestBuild: RequestBuilder = this.http.createRequest('/getPerson/' + personId).asGet();
        let response: HttpResponseMessage = await getRequestBuild.send();
        if(response.isSuccess) {
            return response.content;
        } else {
            return null;
        }
    }

    public async deletePerson(personId: string): Promise<Person> {
        let deleteRequestBuild: RequestBuilder = this.http.createRequest('/deletePerson/' + personId).asDelete();
        let response: HttpResponseMessage = await deleteRequestBuild.send();
        if(response.isSuccess) {
            return response.content;
        } else {
            return null;
        }
    }


}