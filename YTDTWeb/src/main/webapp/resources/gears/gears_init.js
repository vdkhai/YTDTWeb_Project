// Copyright 2007, Google Inc.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
//  1. Redistributions of source code must retain the above copyright notice,
//     this list of conditions and the following disclaimer.
//  2. Redistributions in binary form must reproduce the above copyright notice,
//     this list of conditions and the following disclaimer in the documentation
//     and/or other materials provided with the distribution.
//  3. Neither the name of Google Inc. nor the names of its contributors may be
//     used to endorse or promote products derived from this software without
//     specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
// EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
// OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
// WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
// OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
// ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//
// Sets up google.gears.*, which is *the only* supported way to access Gears.
//
// Circumvent this file at your own risk!
//
// In the future, Gears may automatically define google.gears.* without this
// file. Gears may use these objects to transparently fix bugs and compatibility
// issues. Applications that use the code below will continue to work seamlessly
// when that happens.

(function() {
  // We are already defined. Hooray!
  if (window.google && google.gears) {
    return;
  }

  var factory = null;

  // Firefox
  if (typeof GearsFactory != 'undefined') {
    factory = new GearsFactory();
  } else {
    // IE
    try {
      factory = new ActiveXObject('Gears.Factory');
      // privateSetGlobalObject is only required and supported on WinCE.
      if (factory.getBuildInfo().indexOf('ie_mobile') != -1) {
        factory.privateSetGlobalObject(this);
      }
    } catch (e) {
      // Safari
      if ((typeof navigator.mimeTypes != 'undefined')
           && navigator.mimeTypes["application/x-googlegears"]) {
        factory = document.createElement("object");
        factory.style.display = "none";
        factory.width = 0;
        factory.height = 0;
        factory.type = "application/x-googlegears";
        document.documentElement.appendChild(factory);
      }
    }
  }

  // *Do not* define any objects if Gears is not installed. This mimics the
  // behavior of Gears defining the objects in the future.
  if (!factory) {
    return;
  }

  // Now set up the objects, being careful not to overwrite anything.
  //
  // Note: In Internet Explorer for Windows Mobile, you can't add properties to
  // the window object. However, global objects are automatically added as
  // properties of the window object in all browsers.
  if (!window.google) {
    google = {};
  }

  if (!google.gears) {
    google.gears = {factory: factory};
  }
})();

//--------------------
function isDefined(type) {
  return (type != 'undefined' && type != 'unknown');
}

function childNodes(element) {
  if (isDefined(typeof element.childNodes)) {
    return element.childNodes;
  } else if (isDefined(typeof element.children)) {
    return element.children;
  }
}

function getElementById(element_name) {
  if (isDefined(typeof document.getElementById)) {
    return document.getElementById(element_name);
  } else if(typeof isDefined(document.all)) {
    return document.all[element_name];
  }
}

function setTextContent(elem, content) {
  if (isDefined(typeof elem.innerText)) {
    elem.innerText = content; 
  } else if (isDefined(typeof elem.textContent)) {
    elem.textContent = content;
  }
}

function setupSample() {
  // Make sure we have Gears. If not, tell the user.
  if (!window.google || !google.gears) {
    if (confirm("This demo requires Gears to be installed. Install now?")) {
      location.href = "http://gears.google.com/";
      return;
    }
  }

  var viewSourceElem = getElementById("view-source");
  if (!viewSourceElem) {
    return;
  }
  var elm;
  if (navigator.product == "Gecko") {
    // If we're gecko, we can show the source of the application with the
    // view-source protocol.
    elm = "<a href='view-source:" + location.href + "'>" +
          "View Demo Source" +
          "</a>";
  } else {
    // Otherwise, just tell users how to do it manually.
    elm = "<em>" +
          "To see how this works, use the <strong>view " +
          "source</strong> feature of your browser" +
          "</em>";
  }
  viewSourceElem.innerHTML += elm;
}

function checkProtocol() {
  if (location.protocol.indexOf('http') != 0) {
    setError('This sample must be hosted on an HTTP server');
    return false;
  } else {
    return true;
  }
}

function addStatus(message, opt_class) {
  var elm = getElementById('status');
  var id = 'statusEntry' + (childNodes(elm).length + 1);
  if (!elm) return;
  if (opt_class) {
    elm.innerHTML += '<span id="' + id + '" class="' + opt_class + '"></span>';
  } else {
    elm.innerHTML += '<span id="' + id + '"></span>';
  }
  elm.innerHTML += '<br>';
  setTextContent(getElementById(id), message);
}

function clearStatus() {
  var elm = getElementById('status');
  elm.innerHTML = '';
}

function setError(s) {
  clearStatus();
  addStatus(s, 'error');
}

setupSample();

