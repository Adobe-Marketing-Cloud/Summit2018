Exercise 3 - Create a CIF Package with Action Sequence
========================================================

## Objective
![Image of ex3 outcome](https://github.com/Adobe-Marketing-Cloud/adobe-cif-extension-sample/blob/master/Resources/ex3.png)

## Tasks

```diff
- **WARNING: PLEASE REPLACE seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME} with your firstname and lastname: seat-john-doe**
```

1. If you don't have a package `seat-john-doe` created please follow steps in [Exercise 1](../exercise-01/tutorial-01-hello-world.md) or [Exercise 2](../exercise-02/tutorial-02-hello-world-sequence.md)

2. Create Action 

    ```ruby
    wsk action create seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME}/applyDiscount applyDiscount.js --param discountCategory 743fd9df-6534-4962-85ab-6cc5e55635c7
    ```

	For the "discountCategory" parameter we provide a default value, which is used if category is not provided as a URL parameter. The category used here is Men's Coats. See step 6.

	Example:

    ```ruby
    wsk action create seat-john-doe/applyDiscount applyDiscount.js --param discountCategory 743fd9df-6534-4962-85ab-6cc5e55635c7
    ```

3. Create a Action Sequence

	```ruby
    wsk action create seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME}/getDiscountedProducts --sequence "getProductsService,seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME}/applyDiscount,webActionTransformer" --web true
    ```

    Expected output
    ```ruby
    ok: created action seat-X-X/getDiscountedProducts
    ```
    
    Learn more about [web actions](https://github.com/apache/incubator-openwhisk/blob/master/docs/webactions.md)

4. Check if the action sequence is created

	```ruby
    wsk action list
    ```

    Expected output
   
    ```ruby 
    actions
    /summit2018-L735/seat-X-X/applyDiscount                                private nodejs:6
    /summit2018-L735/seat-X-X/hello-world                                  private nodejs:6
    /summit2018-L735/seat-X-X/getDiscountedProducts                        private sequence
    /summit2018-L735/searchProducts                                        private sequence
    /summit2018-L735/postShippingMethod                                    private sequence
    /summit2018-L735/postPayment                                           private sequence
    ...
    ```

5. Check if the newly created sequence has the right actions configured 

	```ruby
    wsk action get seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME}/getDiscountedProducts
    ```

   Expected output
   
   ```ruby 
   ok: got action seat-john-doe/getDiscountedProducts
   {
       "namespace": "summit2018-L735/seat-X-X",
       "name": "getDiscountedProducts",
       "version": "0.0.1",
       "exec": {
           "kind": "sequence",
           "components": [
               "/summit2018-L735/getProductsService",
               "/summit2018-L735/seat-X-X/applyDiscount",
               "/summit2018-L735/webActionTransformer"
           ]
       },
       "annotations": [
           {
               "key": "web-export",
               "value": true
           },
           {
               "key": "raw-http",
               "value": false
           },
           {
               "key": "final",
               "value": true
           },
           {
               "key": "exec",
               "value": "sequence"
           }
       ],
       "parameters": [
           {
               "key": "_actions",
               "value": [
                   "/summit2018-L735/getProductsService",
                   "/summit2018-L735/seat-X-X/applyDiscount",
                   "/summit2018-L735/webActionTransformer"
               ]
           }
       ],
       "limits": {
           "timeout": 60000,
           "memory": 256,
           "logs": 10
       },
       "publish": false
   }
   
   ```
6. Open PostMan and run the following command and see the response (as you can see discount is applied)

	**Sample call 1**: apply discount to default category - men's coats
	```ruby
    GET https://runtime.adobe.io/api/v1/web/summit2018-L735/seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME}/getDiscountedProducts.http?text=jacket
    ```
	discount is applied to all product in the men's coat category, product name is suffied as well

	**Sample call 2**: apply discount to default category provide as param
	```ruby
    GET https://runtime.adobe.io/api/v1/web/summit2018-L735/seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME}/getDiscountedProducts.http?text=shirt&discountCategory=1146e785-0a44-47d7-a9d4-744f219843fd
    ```
	discount is applied to all product in the men's > shirts category, product name is suffied as well

7. **Bonus**: Debug I/O Runtime actions

    Run the OpenWhisk Cli to pool all action invocation messages
    ```ruby
    wsk activation poll
    ````

    Re-run URL calls from Step 6.

    Expected output: get information and logging information from you actions

    Hint: you can also do a combination of `wsk activation list` and `wsk activation get <id>` to get action invocation details.

8. Overall outcome

![Image of ex3 outcome](https://github.com/Adobe-Marketing-Cloud/adobe-cif-extension-sample/blob/master/Resources/ex3.png)  


