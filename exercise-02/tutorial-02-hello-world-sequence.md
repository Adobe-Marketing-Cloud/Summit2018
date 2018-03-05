Exercise 2 - Create a Adobe I/O Hello World Sequence
===========

## Objective: 

![Image of ex2 outcome](https://github.com/Adobe-Marketing-Cloud/adobe-cif-extension-sample/blob/master/Resources/ex2.png)	

## Tasks

```diff
- WARNING: Steps 1 - 5 may be repetition for you, if you have done other exercises
```

1. Open Terminal.

2. Go to into a workspace (Example: If your workspace directory is *Desktop/l735*) 
	```ruby
	cd Desktop/l735
	```
3. Checkout all code from Adobe Central GitHub repository
	```ruby
	git clone https://github.com/Adobe-Marketing-Cloud/adobe-cif-extension-sample.git 
	```
4. Go into adobe-cif-extension-sample
	```ruby
	cd adobe-cif-extension-sample/exercise-02
	```
5. Create a OpenWhisk Package (make sure to use a format seat-firstname-lastname)
	```diff
	- WARNING: PLEASE REPLACE seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME} with your FIRSTNAME and LASTNAME: Example: seat-john-doe -
	```
	
	```ruby
	wsk package create seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME}
	```
	
6. Let us create a sequence using two actions sequence files hello_sequence_action1.js and hello_sequence_action2.js.  		
	```diff
	- WARNING: PLEASE REPLACE seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME} with your FIRSTNAME and LASTNAME: Example: seat-john-doe -
	```
	
	```ruby
	wsk action create seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME}/action1 hello_sequence_action1.js
	```
	
	```ruby
	wsk action create seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME}/action2 hello_sequence_action2.js
	```
	
6. Let's create a sequence with these two sequences
	```ruby
	wsk action create seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME}/hello-world-sequence --sequence seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME}/action1,seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME}/action2
	```
	
7. Let's invoke 
	```ruby
	wsk action invoke  seat-{YOUR_FIRSTNAME}-{YOUR_LASTNAME}/hello-world-sequence --result --param text "Hello, my name is Larry"
	```
	
8. Expected output
	```ruby
		{
	    "array": [
		"Larry",
		"is",
		"name",
		"my",
		"Hello,"
	    ]
	}
	```

9. Overall outcome

![Image of ex2 outcome](https://github.com/Adobe-Marketing-Cloud/adobe-cif-extension-sample/blob/master/Resources/ex2.png)	

10. Learn more about [OpenWhisk sequence] (https://www.raymondcamden.com/2017/01/06/an-example-of-an-openwhisk-sequence)
	
