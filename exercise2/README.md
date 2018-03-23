# Exercise 2 - Instance Creation and Training

⏳Time Allocated: 15 minutes

Login URL:
- Adobe Cloud Platform: https://ui-prod1-va7.cloud.adobe.io/

Concepts:

![lightbulb](../images/lightbulb.jpg)  **Recipe** - A recipe is a propriety machine learning or AI algorithm, or an ensemble of machine learning or AI algorithms, to help solve specific business problems.

![lightbulb](../images/lightbulb.jpg) **Instance** - An instance is an occurrence of the recipe configured with the right data definition to help solve specific business problems.

![lightbulb](../images/lightbulb.jpg) **Trained Model** - A trained model is an instance (of the recipe) that is trained using historical data to learn from. The historical data must contain the correct answer, also known as the target or target attribute. The trained model finds patterns in the training data to help predict the target and uses that knowledge to predict the target for new sets of data where the target is unknown.

![lightbulb](../images/lightbulb.jpg) **Experiment** - An experiment is the process of creating a trained model by training the instance with a portion of the live production data.

Objectives:
- Create a New Instance of a Recipe
- Run Experiments to Train Your Instance
- Evaluate the Performance of Your Experiments
- Identify the Experiment that you would like to Publish

## Log into the Adobe Cloud Platform

You may skip this step if you are already logged in.

1. Navigate to: https://ui-prod1-va7.cloud.adobe.io/
2. Authenticate using the provided credentials

## Explore Available Recipes

Explore the various Recipes. For this lab we will be using the **Product Purchase Prediction** Recipe. You may notice that the **Create Instance** option is enabled for this Recipe in your account.

1. Navigate to **AI Studio**
2. Explore the available Recipes
3. Where available, select **Open** and review the descriptions

![Product Purchase Prediction](../images/recipe.jpg)


## Create a New Instance of the Product Purchase Prediction Recipe

⚠When prompted, add a unique identifier to the **Instance Name** e.g. 'Product Purchase Prediction - [Last Name]'

1. **Open** the **Product Purchase Prediction** Recipe
2. Review the details
3. Select **Create Instance** 
4. Provide an Instance Name and Description

## Run an Experiment Using Default Settings

⏳Experiments may take up to 5 minutes to complete

1. On your Instance details page select **Create Experiment**
2. Select Your Data Source: Choose **ProductPurchaseTrainingData**
3. Select **Run**

## Run Additional Experiments

You may run multiple experiements at the same time

1. On your Instance details page select **Create Experiment**
2. Select Your Data Source: Choose **ProductPurchaseTrainingData**
3. Drag additional features into the Features Input area. Adding additional features may increase the accuracy of your Trained Instance
4. Adjust the Parameters. NumTrees and MaxDepth are hyper-parameters. Hyper-parameters cannot be learned, they must be assigned before training of the model. Adjusting the parameters may change the accuracy of the Trained Model.
5. Select **Run**

🔬 Configuring Features

Features that have a lock icon are required and cannot be removed. The additional Available Features are optional.

🔬 Configuring Parmeters

| Parameter | Description | Recommended Range |
|:------|:------|:------|
| NumTrees |  The number of trees to create for the Random forest algorithm | 25 - 50 |
| MaxDepth | The maximum depth of each tree for mining rules for Random forest algorithm | 5 - 15 |


## Evaluate the Performance of Your Experiments

1. Review the **Evaluation Metrics** of each Experiment executed by clicking on the name
2. Explore the information provided for each metric
3. Identify the top performing Experiment

![Evaluation Metrics](../images/metrics.jpg)

## Next Steps

In the next exercise you will see how to publish an Experiment and begin making predictions.

Let's go! ➡[Exercise 3](../exercise3/README.md)
