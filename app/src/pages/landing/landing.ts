import { Component } from '@angular/core';
import { IonicPage, NavParams } from 'ionic-angular';
import {Settings} from "../../providers/settings/settings";

@IonicPage()
@Component({
    selector: 'page-landing',
    templateUrl: 'landing.html'
})
export class LandingPage {

    public username: string;

    constructor(private navParams: NavParams, private settings: Settings) {
        console.log("In Landing Page constructor");
        this.username = "";
        this.settings.load();
            //Coming in from the login screen
            console.log("Landing Page: Get User details");
    }
}