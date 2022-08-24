// let script = document.createElement('script');
// script.src = "https://code.jquery.com/jquery-2.2.4.js";
// script.integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=";
// script.crossOrigin="anonymous";
// document.getElementsByTagName('head')[0].appendChild(script);
//
// script = document.createElement('script');
// script.src = "//code.jquery.com/ui/1.11.2/jquery-ui.js";
// document.getElementsByTagName('head')[1].appendChild(script);
//
// script = document.createElement('script');
// script.src = "//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js";
// document.getElementsByTagName('head')[2].appendChild(script);

function displayContents(contents) {
    let element = document.getElementById('file-content');
    element.hidden = false;
    element.textContent = contents;
}

$(document).ready(function() {
    // file upload
    $("#loadFile").change((e) => {
        console.log("FILES:", e.target.files);  // ARRAY (contains file OBJECT)
        let file = e.target.files[0];
        if (!file) {
            return;
        }
        console.log("FILE:", file);
        let reader = new FileReader();          // OBJECT
        reader.onload = function(e) {
            let contents = e.target.result;     // STRING
            console.log("TYPE:", typeof contents);
            displayContents(contents);
            $.ajax({
                // https://electronics.local:9002/testingstorefront/my-account/parseXmlFile
                url: '${contextPath}/my-account/parseXmlFile',
                    type: "post",
                    contentType: "multipart/form-data",
                    processData: false,
                    data: contents,
                    success: function(response) {
                    console.log("RESPONSE:", response);
                    let element = document.getElementById('file-response');
                    element.hidden = false;
                    element.textContent = response;
                }
            });
        };
        reader.readAsText(file);

        // ACC.config.CSRFToken = '${ycommerce:encodeJavaScript(CSRFToken.token)}';
        // send ACC.config.CSRFToken when using ajax form (from javaScriptVariables.tag)
    });

    // popup
    $("#formSubmit").click(() => {
        console.log("Delete file content?");
        if (confirm("Delete file content?")) {
            // $("#formId").attr("method", "post").attr("action", "").submit();
            document.getElementById('file-content').innerText = "";
        }
    })
    // modal
    $("#btnShow").click(function () {
        console.log("Modal button clicked");
        $('#SampleModal').modal('show');
        $("#save").click(() => {
            console.log("Save button clicked");
            // $('#SampleModal').show();
            $('#SampleModal').modal('hide');
        });
        $("#close").click(() => {
            console.log("Cancel button clicked");
            $('#SampleModal').modal('hide');
            // $("#getUsers").attr('method', 'post').attr('action', 'registerUser').submit();
        });
    });
});