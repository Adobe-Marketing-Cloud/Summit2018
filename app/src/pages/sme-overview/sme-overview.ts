import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import {IonicPage, MenuController, NavParams, NavController} from 'ionic-angular';
import {Products} from "../../providers/products/products";
import {SETTINGS_PRODUCT_CATEGORIES_KEY, SERVER_URL} from '../../providers/config';
import {Settings} from '../../providers/settings/settings';
import {SMEDetailPage} from '../pages';

@IonicPage()
@Component({
    selector: 'page-sme-overview',
    templateUrl: 'sme-overview.html'
})
export class SMEOverviewPage {

    public categories: any;
    //public productCategories: any;
    //private access_token: string;

    constructor(public menuCtrl: MenuController, public navParams: NavParams, public navCtrl: NavController, public translateService: TranslateService, private products: Products, private settings: Settings) {
        console.log("In SMEOverviewPage constructor");
        let cats = navParams.get("childCategory");
        if(cats != null) {
            console.log("Categories passed in via nav params: "+JSON.stringify(cats));
            this.categories = cats;
        } else{
            this.settings.getValue(SETTINGS_PRODUCT_CATEGORIES_KEY).then((val) => {
                this.categories = val;
                if(this.categories != null){
                    console.log("Categories from stored settings: "+JSON.stringify(this.categories));
                    console.log("Finished constructor in SME Overview page. title: "+this.categories.title);
                } else{
                    this.products.getCategories().map((res)=>res.json()).subscribe((res)=>{
                        console.log("Product Categories: "+JSON.stringify(res));
                        this.categories = res;
                        //Save categories JSON
                        this.settings.setValue(SETTINGS_PRODUCT_CATEGORIES_KEY,this.categories);
                        console.log("Finished constructor in SME Overview page. title: "+this.categories.title);
                    }, (err)=>{
                        console.error("Error getting Categories: "+err.message);
                    });
                }
            });
        }
    }

    getImage(image: string): string{
        return SERVER_URL+image;
    }

    openCategory(child){
        console.log("Product is "+child.product);
        if(child.product === true){
            console.log("Pushing to SME Details page");
            this.navCtrl.push(SMEDetailPage,{"product" : child});
        } else{
            console.log("Pushing to SME Overview page for child category");
            this.navCtrl.push(SMEOverviewPage,{"childCategory" : child});
        }
    }
}
