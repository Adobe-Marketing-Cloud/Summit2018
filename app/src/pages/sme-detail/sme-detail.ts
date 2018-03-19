import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import {IonicPage, NavController, MenuController, NavParams} from 'ionic-angular';
import {Products} from '../../providers/products/products';
import {Settings} from '../../providers/settings/settings';
import {SERVER_URL} from '../../providers/config';
import {SMEOverviewPage} from '../sme-overview/sme-overview';

@IonicPage()
@Component({
    selector: 'page-sme-detail',
    templateUrl: 'sme-detail.html'
})
export class SMEDetailPage {

    public product: any;
    public productDetails: any;

    constructor(public menuCtrl: MenuController, public translateService: TranslateService, public navParams: NavParams, public navCtrl: NavController, private products: Products, private settings: Settings) {
        console.log("In SMEDetailPage constructor");
        let pdt = navParams.get("product");
        if(pdt != null) {
            console.log("Categories passed in via nav params: "+JSON.stringify(pdt));
            this.product = pdt;
            this.settings.getValue(this.product.productPath).then((val) => {
                this.productDetails = val;
                if(this.productDetails != null){
                    console.log("Product Details from stored settings: "+JSON.stringify(this.productDetails));
                    console.log("Finished constructor in SME Details page");
                } else{
                    this.products.getProductDetails(this.product.productPath).map((res)=>res.json()).subscribe((res)=>{
                        console.log("Product Categories: "+JSON.stringify(res));
                        this.productDetails = res;
                        //Save categories JSON
                        this.settings.setValue(this.product.path,this.productDetails);
                        console.log("Finished constructor in SME Details page.");
                    }, (err)=>{
                        console.error("Error getting Product Details: "+err.message);
                    });
                }
            });
        } else{
            console.error("SME Details Page: No product parameter passed to this page. Do not know which product to display. Sending back to previous location");
            this.navCtrl.push(SMEOverviewPage);
        }
    }

    getImage(image: string): string{
        return SERVER_URL+image;
    }
}
