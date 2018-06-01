import {inject} from 'aurelia-framework';
import {EventAggregator, Subscription} from "aurelia-event-aggregator";
import {Person} from '../../services/person-service'
import {PersonService} from '../../services/person-service'

@inject(PersonService, EventAggregator)
export class PersonUi {

    private people: Person[] = [];

    private currentForename: string = '';
    private currentSurname: string = '';

    private personServiceSubscription: Subscription;

    constructor(private personService: PersonService, private ea: EventAggregator) {
    }

    public attached(): void {
        this.personServiceSubscription =
            this.ea.subscribe(PersonService.EVENT_TOPIC_PERSON_LIST_UPDATE, response => {
                console.log("Received person list update notification");
                this.updatePeopleArray();
            });
        this.updatePeopleArray();

    }

    public detached(): void {
        this.personServiceSubscription.dispose();
    }

    public async addPerson(): Promise<void> {
        if(this.isDefinedAndNotEmptyString(this.currentForename.trim()) && this.isDefinedAndNotEmptyString(this.currentSurname.trim())) {
            await this.personService.addPerson(this.currentForename.trim(), this.currentSurname.trim());
            this.currentForename = '';
            this.currentSurname = '';
            this.updatePeopleArray();
        }
    }

    public isDefinedAndNotEmptyString(theString: string): boolean {
        return theString !== undefined && theString !== null && theString.trim() !== '';
    }

    public async deletePerson(person: Person): Promise<void> {
        await this.personService.deletePerson(person.id);
        this.updatePeopleArray()
    }

    private async updatePeopleArray(): Promise<void> {
        let resultPeopleArray: Person[] = await this.personService.getAllPeople();
        this.people = resultPeopleArray;
    }
}