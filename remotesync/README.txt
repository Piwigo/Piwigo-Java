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

#checkout remote sync
svn checkout http://piwigo.org/svn/extensions/piwigo-remote-sync
cd piwigo-remote-sync

#install build system
apt-get install maven2

#build remote sync without tests
cd remotesync
mvn -Dmaven.test.skip=true clean package

#show help
java -jar target/remotesync.jar -help
java -jar target/remotesync-ui.jar -help

#launch remote sync ui
java -jar target/remotesync-ui.jar
