# Piwigo-Java / Piwigo Remote Sync

**Piwigo Remote Sync** is an **uploader** for the **Piwigo photo** gallery software

* Piwigo-Java is a GitHub project for the Piwigo Remote Sync Java application
* Piwigo Remote Sync is also a reusable API for the Piwigo photo gallery software
* Piwigo Remote Sync is available in **alpha** version

# Requirements

* A **Java** jre/jdk (version **6** or greater)
* A **Piwigo** working **installation** (version **2.6** or greater)

# Quick start

1. **Download** the latest **remotesync-ui.jar** from https://github.com/Piwigo/Piwigo-Java/releases
2. **Double click** on it (your Operating System should use your default Java installation to execute the application)

# FAQ

* **Is there any documentation available**
* Not yet

* **What does "Sync" means**
* Right, it means "upload". This application will only upload new local images to your remote gallery

* **Is there any link to [Piwigo Import Tree](http://piwigo.org/doc/doku.php?id=user_documentation:tools:piwigo_import_tree) ?**
* Yes. As of 0.0.x, we try to offer the same level of functionality. Right now, we use the same cache mechanism (upload each local file once based on it's filename)   

# Troubleshooting

Using a shell, start the application with

`java -jar /tmp/remotesync-ui.jar -debug`

# License

Piwigo-Java / Piwigo Remote Sync is released under the GPL v2 license.
