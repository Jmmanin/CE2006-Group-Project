import urllib.request
req = urllib.request.Request('https://data.gov.sg/api/action/datastore_search?resource_id=9326ca53-9153-4a9c-b93f-8ae032637b70', headers={'User-Agent': 'Mozilla/5.0'})
html = urllib.request.urlopen(req).read()
file = open("grademploymentsurvey.txt", "w")
file.write(html.decode("utf-8"))
print(html)