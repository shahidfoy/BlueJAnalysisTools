# ArtisanCQ

A code quality software extension for the [BlueJ](https://www.bluej.org) IDE.

Artisan Code Quality is intended to support apprentice developers in their pursuit of becoming skilled craftspeople, and support instructors in their pursuit of training those apprentice developers, by providing a platform that integrates code quality analysis tools with BlueJ. To this end, it renders code quality analysis metrics from supported tools in a manner that is easy to use, build, install, maintain, and extend. The quality of the product itself is an attempt by a team of journeymen to model artisanal development processes, and it is designed with the goal of being an exemplar of code craftsmanship.

Written by the **Artisan Software** team for Dr. Paul's CS4250 2017 Fall Semester.

- Mohammad Foy
- Jonathan Grant
- Jonathan Nicholson
- Said Robley

## Installation and Use

- Those interested in obtaining a ready-to-install version of Artisan Code Quality may find it under `build/package/artisancq.zip`. Or, you may follow this URL to the most recent release: https://gouda.msudenver.edu/cs4250/JavaProgramAnalysis/ArtisanSoftware/trunk/build/package/artisancq.zip
The zip contains an `artisancq.jar` file and an `artisancq/` directory with supporting tool
jar files required for static analysis of files within Blue.

- Download the zip and copy the zip file into the extension install location based on your operating system, by following the directions in BlueJ documentation regarding how to install extensions: https://www.bluej.org/extensions/extensions.html

- After copying the zip into the directory above, unzip the file and it will place
the `artisancq.jar` in the current directory, and the supporting `artisancq/` directory will be available for use.

- When you start BlueJ, the "ArtisanCQ" submenu will appear in BlueJ's tools menu when a project is open. Clicking the items in this submenu will, in a future release, provide reports of code quality analysis for classes in your BlueJ project corresponding to the tool selected.

## Development

This project is written with Java 8 and uses the Apache [Ant][1] and [Ivy][2] build tools.
Be sure to install Ant on your local system to build this project. The Ivy dependency self-bootstraps so there is no need to install it.
The project only supports the use of Java 8 for building.

1. The first time you checkout the project, all the dependencies you need to successfully build are
already checked into the project.

2. Running `ant package` (also the default `ant` task) will compile everything in `src/main`, then create a jar of the result and place it under the `build/jars` directory, then archive all the runtime dependencies with the jar and place the archive under `build/package` directory.

3. You can update the dependencies of this project by modifying the versions required in `ivy.xml` and
run `ant install`. This will place dependency jars into the `lib` directory of this project and will
be available for the compiler to pull into the project. Rerun `ant package` to make a new extension
with these updated dependencies.

3. You can run the tests by running `ant test`. This will compile the JUnit tests defined in `src/test`, run them, and output the results to the screen.

### Ignoring files

Checked into this project is a [`.svnignore` file][3]. Run the below
command to ignore files that shouldn't be added to the repo.

```
svn propset svn:ignore -F .svnignore .
```

If you generate or add a new file that should not be checked in, add
the file and/or folder to the `.svnignore` file and run the command again.


[1]: http://ant.apache.org
[2]: http://ant.apache.org/ivy/
[3]: http://superchlorine.com/2013/08/getting-svn-to-ignore-files-and-directories/



============================================
How to work and install PMD functionality.
==============================
download PMD at https://pmd.github.io/.  Grab the PMD folder and place it in your root directory.  for windows the folder location should look like:
 
"C:/PMD"

only works for windows right now.  

once the PMD folder is added to the C:/ directory run commands "ant install" and "ant"
then locate the jar in "build/jar" and place it into bluej "lib/extensions"

For mac users:
Try to place PMD folder into root directory
so it should look like this:

"/PMD"

Also this only works for one java file at a time. 
To see it working right click on the java file you would like to inspect and click on "ArtisanCQ->PMD Analysis


============================================
How to work and install FindBug functionality.
==============================
download FindBugs at https://sourceforge.net/projects/findbugs/.  Create a FINDBUGS directory in your root folder for windows it should look like:

"C:/FINDBUGS"

once you find a compressed version of findbugs on the website.  Unzip the file and place its contents into the FINDBUGS folder.  for example the findbugs
bin location should look like this

"C:/FINDBUGS/bin"

only works for windows right now.  

once the PMD folder is added to the C:/ directory run commands "ant install" and "ant"
then locate the jar in "build/jar" and place it into bluej "lib/extensions"

For mac users:
Try to place FINDBUGS folder into root directory
so it should look like this:

"/FINDBUGS"

and the FINDBUGS bin should look as so:

"/FINDBUGS/bin"

Also this checks all java files that exist in the package. 
To see it working right click on the java file you would like to inspect and click on "ArtisanCQ->FindBug Analysis
