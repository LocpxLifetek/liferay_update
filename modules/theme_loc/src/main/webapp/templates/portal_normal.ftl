<!DOCTYPE html>

#parse ($init)

<html class="$root_css_class" dir="#language ("lang.dir")" lang="$w3c_language_id">

<head>
	<title>$the_title - $company_name</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	$theme.include($top_head_include)
</head>

<body class="$css_class">

<a href="#main-content" id="skip-to-content">#language ("skip-to-content")</a>

$theme.include($body_top_include)

#if ($is_signed_in)
	#dockbar()
#end

<div id="wrapper">
	<header id="banner" role="banner">
		<div id="heading">
			<h1 class="site-title">
				<a class="$logo_css_class" href="$site_default_url" title="#language_format ("go-to-x", [$site_name])">
					<img alt="$logo_description" height="$site_logo_height" src="$site_logo" width="$site_logo_width" />
				</a>

				#if ($show_site_name)
					<span class="site-name" title="#language_format ("go-to-x", [$site_name])">
						$site_name
					</span>
				#end

				#if (!$is_signed_in)
					<a href="$sign_in_url" id="sign-in" rel="nofollow">$sign_in_text</a>
				#end

				<div id="page-search">
					$theme.journalContentSearch()
				</div>
			</h1>
		</div>

		#if ($has_navigation || $is_signed_in)
			#parse ("$full_templates_path/navigation.ftl")
		#end
	</header>

	<section id="content">
		<h1 class="hide-accessible">$the_title</h1>

		#if ($show_breadcrumbs)
			<nav class="site-breadcrumbs" id="breadcrumbs">
				<h1 class="hide-accessible">#language ("breadcrumbs")</h1>

				#breadcrumbs()
			</nav>
		#end

		#if ($selectable)
			$theme.include($content_include)
		#else
			$portletDisplay.recycle()

			$portletDisplay.setTitle($the_title)

			$theme.wrapPortlet("portlet.ftl", $content_include)
		#end
	</section>

	#parse ("$full_templates_path/footer.ftl")
</div>

$theme.include($body_bottom_include)

$theme.include($bottom_include)

</body>

</html>