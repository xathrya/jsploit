class JMSFramework

    Indent = '   '

    #require 'rex'
    #require 'msf/ui'
    require 'msf/base'

    def initialize

        $stderr.puts "[*] Please wait while we load the module tree..."
        $framework = Msf::Simple::Framework.create

        if ($framework.modules.failed.length > 0)
          print("Warning: The following modules could not be loaded!\n\n")
          $framework.modules.failed.each_pair do |file, err|
            print("\t#{file}: #{err}\n\n")
          end
        end

    end

    def exploits

        $framework.exploits

    end

    def exploit(exploit_name, payload_name, opts)

        exploit = $framework.exploits.create(exploit_name)

        session = exploit.exploit_simple(
            'Payload' => payload_name,
            'OptionStr' => opts,
            'LocalInput' => Rex::Ui::Text::Input::Stdio.new,
            'LocalOutput' => Rex::Ui::Text::Output::Stdio.new)
        
        if(session)
            output.print_status("Session #{session.sid} created, interacting...")
            output.print_line
            session.init_ui(input, output)
            session.interact
        else
            "Exploit completed, no session was created."
        end

    end

    def payloads

	$framework.payloads

    end

    def version

        $framework.version

    end

end