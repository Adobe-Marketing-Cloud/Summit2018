<div class="aem-logo"></div>
<div class="adobe-logo"></div>

# L725 - Accelerate your development with Experience Manager Core Components 

### Adobe Summit 2018

## Git Branch Structure

Lab 725 resources reside in [https://github.com/Adobe-Marketing-Cloud/Summit2018](https://github.com/Adobe-Marketing-Cloud/Summit2018) @ [Branch L725](https://github.com/Adobe-Marketing-Cloud/Summit2018/tree/L725).

* The folder `files` contains all files used for this lab.
* The folder `ui.apps` contains the the AEM Project that builds `files/l725.ui.apps-1.0.0.zip` and contains all source code for the project.
* The Chapter Solution packages in in `files` contain configuration and content, but no code.

## Fix Package

The package `files/l725.ui.apps.fix-1.0.0.zip` is used to patch installations of ` l725.ui.apps-0.0.1-SNAPSHOT.zip`.
If `file/l725.ui.apps-1.0.0.zip` is used, then the *fix* package is not necessary.

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


