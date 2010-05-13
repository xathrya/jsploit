# Init for MSF Simple Framework
# Could throw this in a scriptlet but lets keep all the ruby seprate :)

# If you really want to though you can end Simple.java and run the scriptlet
# as a String with these two lines.

require 'msf/base'
$framework = Msf::Simple::Framework.create