def call() {
    node {
        stage "checkout"
        // get source code
        checkout scm

        // Respect ci-skip message
        def lastMessage = sh(returnStdout: true, script: 'git log --format=%s -1')
        if (lastMessage.contains('ci-skip')) {
            currentBuild.result = 'ABORTED'
            //error("Not building due to ci-skip")
        }
    }
}