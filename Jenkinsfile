pipeline
{
	agent any

	stages
	{
		stage("Build")
		{
			steps
			{
				echo "Building the code"
				cmd_exec "mvn clean"
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