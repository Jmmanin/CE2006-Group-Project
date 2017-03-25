import urllib.request
req = urllib.request.Request('https://data.gov.sg/api/action/datastore_search?resource_id=eb8b932c-503c-41e7-b513-114cffbe2338', headers={'User-Agent': 'Mozilla/5.0'})
html = urllib.request.urlopen(req).read()
file = open("gradtypeofcourse.txt", "w")
file.write(html.decode("utf-8"))
print(html)