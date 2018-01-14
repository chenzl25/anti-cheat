from joern.all import JoernSteps

j = JoernSteps()

j.setGraphDbURL('http://localhost:7474/db/data/')

# j.addStepsDir('Use this to inject utility traversals')

j.connectToDatabase()

res =  j.runGremlinQuery('getFunctionsByName("main")')
# res =  j.runCypherQuery('...')

for r in res: print r