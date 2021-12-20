<%@ include file="/init.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link
	rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.3.5/jquery.fancybox.min.css"
    />

    <script>
 // Fancybox Config
    $('[data-fancybox="gallery"]').fancybox({
      buttons: ["slideShow", "thumbs", "zoom", "fullScreen", "share", "close"],
      loop: false,
      protect: true,
    });

    </script>
  </head>
  <body>
    <main class="main">
		<div class="container">
		  <div class="card">
			<div class="card-image">
			  <a href="https://bit.ly/34MdBRc" data-fancybox="gallery" data-caption="Caption Images 1">
				<img src="https://bit.ly/34MdBRc" alt="Image Gallery">
			  </a>
			</div>
		  </div>
		  <div class="card">
			<div class="card-image">
			  <a href="https://bit.ly/2Nv9zHh" data-fancybox="gallery" data-caption="Caption Images 1">
				<img src="https://bit.ly/2Nv9zHh" alt="Image Gallery">
			  </a>
			</div>
		  </div>
		  <div class="card">
			<div class="card-image">
			  <a href="https://bit.ly/2q0iuay" data-fancybox="gallery" data-caption="Caption Images 1">
				<img src="https://bit.ly/2q0iuay" alt="Image Gallery">
			  </a>
			</div>
		  </div>
		  <div class="card">
			<div class="card-image">
			  <a href="https://bit.ly/34PEofp" data-fancybox="gallery" data-caption="Caption Images 1">
				<img src="https://bit.ly/34PEofp" alt="Image Gallery">
			  </a>
			</div>
		  </div>
		  <div class="card">
			<div class="card-image">
			  <a href="https://bit.ly/2X4z711" data-fancybox="gallery" data-caption="Caption Images 1">
				<img src="https://bit.ly/2X4z711" alt="Image Gallery">
			  </a>
			</div>
		  </div>
		  <div class="card">
			<div class="card-image">
			  <a href="https://bit.ly/2rtIqMl" data-fancybox="gallery" data-caption="Caption Images 1">
				<img src="https://bit.ly/2rtIqMl" alt="Image Gallery">
			  </a>
			</div>
		  </div>
		  <div class="card">
			<div class="card-image">
			  <a href="https://bit.ly/33xTVAn" data-fancybox="gallery" data-caption="Caption Images 1">
				<img src="https://bit.ly/33xTVAn" alt="Image Gallery">
			  </a>
			</div>
		  </div>
		  <div class="card">
			<div class="card-image">
			  <a href="https://bit.ly/2K3jaDa" data-fancybox="gallery" data-caption="Caption Images 1">
				<img src="https://bit.ly/2K3jaDa" alt="Image Gallery">
			  </a>
			</div>
		  </div>
		  <div class="card">
			<div class="card-image">
			  <a href="https://bit.ly/2WZ3fe2" data-fancybox="gallery" data-caption="Caption Images 1">
				<img src="https://bit.ly/2WZ3fe2" alt="Image Gallery">
			  </a>
			</div>
		  </div>
		</div>
	  </main>
    </div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.3.5/jquery.fancybox.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.0.1/TweenMax.min.js"></script>
  </body>
</html>
