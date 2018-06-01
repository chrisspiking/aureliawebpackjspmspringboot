import {Router, RouterConfiguration} from "aurelia-router";

export class PersonServiceApp {

    router: Router;

    activate() {
        let outerthis = this;
    }

    public configureRouter(config: RouterConfiguration, router: Router): void {
        this.router = router;
        config.title = 'Person Service App';

        config.map([
            {route: 'home-page', name: 'home-page', moduleId: './features/home-page/home-page', title: 'Person Service App Home', settings: {}},
            {route: '/', name: '/', redirect: 'home-page'}
        ]);

        config.mapUnknownRoutes(() => {
            return '/';
        });
    }
}
