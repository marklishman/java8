var url = new java.net.URL('http://lishman.io');
var html = new java.util.Scanner(url.openStream());
html.useDelimiter('$');
var contents = html.next();
contents;