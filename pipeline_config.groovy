jdk_tool = 'java-21'
deploy_to_sonatype = 'true'
is_github_repo = 'true'
maven_install = 'openpay-maven-3.9.6'
jte {
	pipeline_template = "jar_library"
}

libraries {
	maven
}

application_environments{
    dev{name
        bucket = ''
        source = ''
        destination = ''
        secret_name = 'op-jenkins-secrets'
        key_bucket = ''
        key_path = ''
        sign_apk = 'false'
    }
  sandbox {
        bucket = ''
        source = ''
        destination = ''
        secret_name = 'op-jenkins-secrets'
    }
  prod{
        bucket = ''
        source = ''
        destination = ''
        secret_name = 'op-jenkins-secrets'
    }

}
