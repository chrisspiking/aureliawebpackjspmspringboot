import {Aurelia} from "aurelia-framework";

export function configure(aurelia: Aurelia) {
    let src_dir = 'aurelia_src';

    aurelia.use
        .standardConfiguration()
        .developmentLogging()
        .plugin('aurelia-dialog')
        .globalResources([
                src_dir + '/resources/on-enter'
        ]);

    aurelia.start().then(() => aurelia.setRoot(src_dir + '/person-service-app'));
}