# Summit Headless CMS Demo

This is the code and content package associated with Summit 2018 Lab 274; Headless Adobe Experience Manager — Beyond content repository.


## Setup

This lab uses AEM 6.4 Beta Load 20. Any release of AEM 6.4 after this should work. The examples use IntelliJ IDE for editing code, however any code editor will do.

To setup the AEM instance with the initial Configurations & Content:

1. Clone this repository
1. From the root of the cloned workspace, execute:

> mvn clean install -PautoInstallPackage

This will install all referenced code/content needed in the workbook steps.

This lab also uses IntelliJ's embedded Maven build tool. Any maven build system will suffice, the command to execute is:

> mvn clean install -PautoInstallPackage

The following links were provided as bookmarks in the Lab; they are provided here as references:

* <http://localhost:4502>
* <https://www.lipsum.com/>
* <https://hipsum.co/>
* <http://localhost:4502/content/we-retail/language-masters/en/experience/api/summit-lab-724.model.json>
* <http://localhost:4502/content/we-retail/language-masters/en/experience/summit-lab-724.model.json>
* <http://localhost:4502/content/we-retail/language-masters/en/experience/api.model.json>



The JSON View in the workbook screenshots was created using Chrome's JSON Viewer extension, available here:

* <https://chrome.google.com/webstore/detail/json-viewer/gbmdgpbipfallnflgajpaliibnhdgobh>




## Building

This project uses Maven for building. Common commands:

From the root directory, run ``mvn -PautoInstallPackage clean install`` to build the bundle and content package and install to a CQ instance.

From the bundle directory, run ``mvn -PautoInstallBundle clean install`` to build *just* the bundle and install to a CQ instance.

## Using with AEM Developer Tools for Eclipse

To use this project with the AEM Developer Tools for Eclipse, import the generated Maven projects via the Import:Maven:Existing Maven Projects wizard. Then enable the Content Package facet on the _content_ project by right-clicking on the project, then select Configure, then Convert to Content Package... In the resulting dialog, select _src/main/content_ as the Content Sync Root.

## Using with VLT

To use vlt with this project, first build and install the package to your local CQ instance as described above. Then cd to `content/src/main/content/jcr_root` and run

    vlt --credentials admin:admin checkout -f ../META-INF/vault/filter.xml --force http://localhost:4502/crx

Once the working copy is created, you can use the normal ``vlt up`` and ``vlt ci`` commands.

## Specifying CRX Host/Port

The CRX host and port can be specified on the command line with:
mvn -Dcrx.host=otherhost -Dcrx.port=5502 <goals>


