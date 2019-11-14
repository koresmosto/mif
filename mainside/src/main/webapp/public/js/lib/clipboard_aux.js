const clipboardImported = document.createElement('script');
clipboardImported.src = 'https://rawcdn.githack.com/zenorocha/clipboard.js/v2.0.4/dist/clipboard.min.js';
document.head.appendChild(clipboardImported);
clipboardImported.onload = function clipboardCopy() {
  var clipboard = new ClipboardJS('.btn');
  clipboard.on('success', function (e) {
    console.log(e);
  });
  clipboard.on('error', function (e) {
    console.log(e);
  });
};
