#-------------------------------------------------------------------------------
# Copyright (c) 2014 Matthieu Helleboid.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the GNU Public License v2.0
# which accompanies this distribution, and is available at
# http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
# 
# Contributors:
#     Matthieu Helleboid - initial API and implementation
#-------------------------------------------------------------------------------

#backward compatibility for org.piwigo.remotesync.api.model 
do not remove fields
modify fields carefully
when adding new field use (required=false) :
	* @Element(required=false)
	* @Attribute(required=false)
	
#to execute tests
copy /remotesync-api/src/test/resources/org/piwigo/remotesync/api/test/testConfig.example 
to /remotesync-api/src/test/resources/org/piwigo/remotesync/api/test/testConfig
and modify fields 
         <url>http://mygallery.piwigo.com</url>
         <username>username</username>
         <password>Base64password==</password>
execute tests with an empty gallery

do not add /remotesync-api/src/test/resources/org/piwigo/remotesync/api/test/testConfig
to scm 
	