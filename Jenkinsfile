pipeline
{
	agent any
	tools {
	Maven 
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
				//bat "mvn test"
			}
		}
		stage("Deploy")
		{
			steps
			{
				echo "Deploying the code"
				//bat "mvn insatll"
			}
		}
	}
}