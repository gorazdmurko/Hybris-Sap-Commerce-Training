<%--
  Created by IntelliJ IDEA.
  User: Gorazd
  Date: 20. 12. 2022
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>

<div class="row" style="margin: 0 auto;">
<%--    <form id="${action.uid}" action="${action.url}" target="_blank">--%>
    <form:form method="post" action="custom/test#video" modelAttribute="TestingEmailForm">
        <label>Custom action</label>
        <button id="${action.uid}" class="button" type="submit">SUBMIT</button>
    </form:form>

    <div>
        <!-- MODAL -->
        <div class="modal-popup" id="modal-popup">
            <div class="modal-popup-header">
                <div class="title">Example Modal</div>
                <button class="close-button">
                    &times;
                </button>
            </div>
            <div class="modal-popup-body">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eligendi vel perspiciatis sed quos sapiente ex ratione sint, voluptas, iusto dolor quis cum praesentium soluta amet excepturi possimus ad, nisi quam.</p>
                <form class="form">
                    <div class="label">
                        <label class="modal-popup-label">e-mail </label>
                    </div>
                    <div class="input">
                        <input type="text" class="user-input"
                               placeholder="Your e-mail here👻" name="email">
                    </div>
                    <div class="submit">
                        <input type="submit" class="submit-button" value="SUBMIT" />
                    </div>
                </form>
            </div>
            <div class="modal-popup-footer">
                <p>Copyright &copy; 2022 My Website</p>
            </div>
        </div>
        <!-- OVERLAY -->
        <div id="overlay"></div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $("#${action.uid}").click((event) => {
            event.preventDefault();

            <%--if (confirm("Want to continue?")) {--%>
            <%--    $("#${action.uid}").submit();--%>
            <%--}--%>

            console.log("UID: #${action.uid}");

            // OPEN
            document.getElementById('overlay').classList.add('active');
            document.getElementById('modal-popup').classList.add('active');

            // CLOSE
            $(".close-button")[0].addEventListener('click', () => {
                document.getElementById('overlay').classList.remove('active');
                document.getElementById('modal-popup').classList.remove('active');
            })
            $("#overlay").click((e) => {
                document.getElementById('overlay').classList.remove('active');
                document.getElementById('modal-popup').classList.remove('active');
            })

            // SUBMIT
            $(".submit-button")[0].addEventListener('click', (e) => {
                e.preventDefault();
                document.getElementById('overlay').classList.remove('active');
                document.getElementById('modal-popup').classList.remove('active');

                <%--$("#TestingEmailForm").submit();--%>
                <%--$("#TestingEmailForm").attr("method", "get").attr("action", "custom/test").submit();--%>
                document.getElementById("TestingEmailForm").submit();
            })
        });
    });
</script>

<style>
    /* reset */
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }

    body {
        font-family: 'Poppins', sans-serif;
        font-size: 16px;
        line-height: 1.5;
        color: #333;
        background-color: #a1c3ff;
    }

    .container {
        max-width: 1100px;
        margin: 0 auto;
        padding: 0 30px;
    }

    .footer .container {
        height: 200px;
        text-align: center;
        padding: 60px 0;
    }

    /* BUTTON */
    .button {
        border: 3px solid #333;
        padding: 10px;
        color: #111;
        font-weight: bold;
        text-decoration: none;
    }
    .button:hover {
        border: 3px solid #fff;
        color: #fff;
        text-decoration: none;
        background-color: #333;
    }

    /* MODAL & OVERLAY */
    .modal-popup {
        position: fixed;
        /* position upper left corner */
        top: 50%;   /* 50% of screen */
        left: 50%;
        /* transform to center - 50% of container */
        transform: translate(-50%, -50%) scale(0);   /* invisible */
        transition: 200ms ease-in-out;
        border: 1px solid black;
        border-radius: 10px;
        z-index: 10;
        background-color: #333;
        width: 600px;
        max-width: 80%; /* 80% of screen */
    }
    .modal-popup.active {
        transform: translate(-50%, -50%) scale(1);
    }

    /* header */
    .modal-popup-header {
        display: flex;
        justify-content: space-between;
        color: #fff;
        padding: 20px;
    }
    .modal-popup-header .title {
        font-size: 1.65em;
        font-weight: bold;
        letter-spacing: 0.2em;
        text-decoration: underline;
        font-family: Arial, Helvetica, sans-serif;
    }
    .modal-popup-header .close-button {
        cursor: pointer;
        border: none;
        outline: none;
        background: none;
        font-size: 1.75rem;
        font-weight: bold;
        color: #fff;
    }

    /* body */
    .modal-popup-body {
        padding: 20px;
        color: #fff;
        height: 300px;
    }

    /* form */
    .modal-popup-label {
        color: #fff;
        padding-top: 15px;
    }
    .modal-popup-body .form {
        /* display: flex;
        justify-content:space-around;
        align-items: center; */
        font-size: 1em;
        margin-top: 60px;
    }
    .modal-popup-body p {
        text-align: justify;
        line-height: 1.8em;
        font-style: italic;
    }
    .form {
        box-sizing: border-box;
    }
    .form .label, .form .input {
        /* box-sizing: border-box; */
        float: left;
    }
    .form .label {
        width: 10%;
    }
    .form .input {
        width: 70%;
    }
    .form .submit {
        width: 20%;
        display: flex;
        justify-content: end;
    }
    .form:after {
        content: "";
        clear: both;
        display: block;
    }
    .modal-popup-body .user-input {
        color: #fff;
        font-size: 1.1em;
        border:none;
        background-color: #333;
        border-bottom: 1px solid #fff;
        padding: 5px 10px;
        outline: none;
        width: 100%;
    }
    .modal-popup-body .submit-button {
        border: 3px solid #000;
        padding: 10px;
        color: #111;
        font-weight: bold;
        text-decoration: none;
        cursor: pointer;
    }
    input[placeholder] {
        text-align: center;
    }
    input[placeholder]:focus::-webkit-input-placeholder {
        transition: text-indent 0.4s 0.1s ease;
        text-indent: -150%;
        opacity: 1;
    }

    /* footer */
    .modal-popup-footer {
        color: #fff;
        /* background-color: #3474e6; */
        padding: 20px;
    }

    /* overlay */
    #overlay {
        position: fixed;
        opacity: 0;
        transition: 200ms ease-in-out;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: rgba(0, 0, 0, .5);
        pointer-events: none;
        /* when overlay is invisible it WON'T capture click events */
    }
    #overlay.active {
        opacity: 1;
        pointer-events: all;
        /* when overlay IS visible it WILL capture click events */
    }
</style>