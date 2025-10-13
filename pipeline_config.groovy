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
