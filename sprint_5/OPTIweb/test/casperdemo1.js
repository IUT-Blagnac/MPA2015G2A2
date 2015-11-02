var casper = require('casper').create();

casper.start('../../bin/OPTIweb.html', function() {
    this.echo(this.getTitle());
    var expected = 'OPTIweb - V0.1';
    this.test.assertTitle(expected,'title = "' + expected + '"');
});

casper.run();
