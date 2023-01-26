pipeline
{
	agent any

	tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven"
    }

    environment {

    PATH = "C:\\WINDOWS\\SYSTEM32"

	}

	stages
	{
		stage("Build")
		{
			steps
			{
				echo "Building the code"
				bat "mvn clean"
			}
		}
		stage("Test")
		{
			steps
			{
				echo "Test the code"
				bat "mvn test"
			}
		}
		stage("Deploy")
		{
			steps
			{
				echo "Deploying the code"
				bat "mvn install"
			}
		}
		stage("DockerImage")
		{
			steps
			{
				echo "building docker image"
				bat "docker build -t docker-jenkins-integration.jar ."
			}
		}
	}
}