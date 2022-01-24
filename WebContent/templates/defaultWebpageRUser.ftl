<#include "header.ftl">

<b>Welcome to Movie Rating App</b><br><br>

<form method="POST" action="rusergui?action=rate">
	<fieldset id="rate">
		<legend>Required Information</legend>
		<div>
			<label>rating</label>
			<input type="text" name="rating" >
	    </div>

		<div>
			<label>uid (Username)</label>
			<input type="text" name="uid" >
	    </div>

		<div>
			<label>mid (Movie)</label>
			<input type="text" name="mid">
	    </div>
	    
	    <div>
			<label>comment</label>
			<input type="text" name="comment">
	    </div>
	    
	</fieldset>
	<button type="submit" id="submit">Submit</button>
</form>
<#include "footer.ftl">