import {inject} from 'aurelia-framework';

@inject(Element)
export class OnEnterCustomAttribute {

    constructor(element:Element) {
        this.element = element;

        this.onKeyPress = (ev:KeyboardEvent) => {
            let key = ev.which || ev.keyCode;
            if (key === 13) {
                this.action();
            }
        };
    }

    element:Element;
    attached() {
        this.element.addEventListener('keyup', this.onKeyPress);
    }
    onKeyPress;

    action;
    valueChanged(func) {
        this.action = func;
    }

    detached() {
        this.element.removeEventListener('keyup', this.onKeyPress);
    }
}