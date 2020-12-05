#!/usr/bin/env groovy
 
def call(branch, url) 
{
def branchName = branch
def urllink = url
 
checkout([
$class: 'GitSCM', 
branches: [[name:"$branchName" ]], 
extensions: [[$class:'WipeWorkspace']],
userRemoteConfigs: [[url: "$urllink" ]]
])
 
}
