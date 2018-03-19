import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import {IonicPage, MenuController, NavParams, NavController} from 'ionic-angular';
import {Speakers} from "../../providers/speakers/speakers";
import {SERVER_URL, CONTENT_FRAGMENT_COMPONENT} from '../../providers/config';

@IonicPage()
@Component({
    selector: 'page-summit-speakers',
    templateUrl: 'summit-speakers.html'
})
export class SummitSpeakersPage {

    public speakers: Array<{speaker: any}> = [];
    private items: any;
    public title: string;
    public text: string;
    public itemsOrder: any;
    private shownGroup: any;

    constructor(public menuCtrl: MenuController, public navParams: NavParams, public navCtrl: NavController, public translateService: TranslateService, private speakersService: Speakers) {
        console.log("In Summit Speakers constructor");
        this.speakersService.getSpeakers().map((res)=>res.json()).subscribe((res)=>{
            console.log("Items: "+JSON.stringify(res));
            this.itemsOrder = res[":items"]["root"][":itemsOrder"];
            this.items = res[":items"]["root"][":items"];
            this.title = this.items["title"]["text"];
            this.text = this.items["text"]["text"];

            this.itemsOrder.forEach(function(itemKey) {
                var currItem = this.items[itemKey];
                if(currItem[":type"] === CONTENT_FRAGMENT_COMPONENT){
                    this.speakers.push({speaker: currItem});
                }
            }, this);

        }, (err)=>{
            console.error("Error getting Speakers: "+err.message);
        });
    }

    getImage(image: string): string{
        return SERVER_URL+image;
    }

    toggleGroup(group) {
        if (this.isGroupShown(group)) {
            this.shownGroup = null;
        } else {
            this.shownGroup = group;
        }
    }

    isGroupShown(group) {
        return this.shownGroup === group;
    }

}
