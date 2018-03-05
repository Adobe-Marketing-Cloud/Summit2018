function main(params) {

    let discountToApply = 0.05 // 5% discount
    let discountText = " - Today 5% off"

    let discountCategory = params.discountCategory
    console.log("[Debug] apply discount to category " + discountCategory)

    // get the product data from previous action and loop
    let products = params.response.body.results
    products.forEach(product => {
        let categories = product.categories.filter(category => category.id === discountCategory);

        // only apply discount if the product belongs to the discounted category
        if (categories[0] != null) {
            // apply message to product title
            Object.keys(product.name).map(key => {
                product.name[key] += discountText
            })

            // apply discount to all product prices
            product.variants.map(variant => {
                return variant.prices.map(price => {
                    price.centAmount -= price.centAmount * discountToApply
                    return price
                })
            })
        }

    });

    return params
}