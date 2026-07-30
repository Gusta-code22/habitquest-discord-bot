package br.com.Gusta_code22.service;

import br.com.Gusta_code22.client.HabitQuestApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiStatusService {
    private final HabitQuestApiClient apiClient;

    public String checkApi(){
        try {

            if ("UP".equals(apiClient.getHealth().status())){
                return "🟢 HabitQuest API ONLINE";
            }

            return "🟡 HabitQuest API instável";

        } catch (Exception e) {
            return "🔴 HabitQuest API OFFLINE";
        }

    }
}
