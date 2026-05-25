<%@ taglib prefix="c"
    uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            height: 100vh;
            background: linear-gradient(135deg, #0f172a, #1e293b);
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
            overflow: hidden;
        }

        .container {
            text-align: center;
            padding: 40px;
            border-radius: 20px;
            background: rgba(255,255,255,0.05);
            backdrop-filter: blur(10px);
            box-shadow: 0 10px 30px rgba(0,0,0,0.4);
            animation: fadeIn 1s ease;
        }

        .error-code {
            font-size: 120px;
            font-weight: bold;
            color: #ef4444;
            text-shadow: 0 0 20px rgba(239,68,68,0.6);
            animation: float 2s infinite ease-in-out;
        }

        .message {
            font-size: 24px;
            margin-top: 10px;
            margin-bottom: 20px;
        }

        .desc {
            color: #cbd5e1;
            margin-bottom: 30px;
        }

        .btn {
            padding: 12px 25px;
            border: none;
            border-radius: 10px;
            background: #3b82f6;
            color: white;
            cursor: pointer;
            font-size: 16px;
            transition: 0.3s;
        }

        .btn:hover {
            background: #2563eb;
            transform: scale(1.05);
        }

        .circle {
            position: absolute;
            border-radius: 50%;
            background: rgba(255,255,255,0.05);
            animation: move 10s linear infinite;
        }

        .circle:nth-child(1) {
            width: 200px;
            height: 200px;
            top: -50px;
            left: -50px;
        }

        .circle:nth-child(2) {
            width: 300px;
            height: 300px;
            bottom: -100px;
            right: -100px;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes float {
            0%,100% {
                transform: translateY(0);
            }
            50% {
                transform: translateY(-10px);
            }
        }

        @keyframes move {
            0% {
                transform: rotate(0deg) translateX(20px) rotate(0deg);
            }
            100% {
                transform: rotate(360deg) translateX(20px) rotate(-360deg);
            }
        }
    </style>
</head>
<body>

    <div class="circle"></div>
    <div class="circle"></div>

    <div class="container">
        <div class="error-code" id="errorCode">
            <c:out value="${statuss}" />
        </div>

        <div class="message">
             <c:out value="${message}" />
        </div>

        <div class="desc">
            Halaman yang kamu cari mungkin sudah dipindahkan atau dihapus.
        </div>

        <button class="btn" onclick="goHome()">
            Kembali
        </button>
    </div>

    <script>
        function goHome() {
            window.history.back();
        }

        const codes = [ <c:out value="${status}" />];
        let index = 0;

        setInterval(() => {
            index = (index + 1) % codes.length;
            document.getElementById("errorCode").innerText = codes[index];
        }, 3000);
    </script>

</body>
</html>