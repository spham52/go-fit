package com.gofit.service;

import com.gofit.dao.CardioLogDAO;
import com.gofit.entity.ActivityLog;
import com.gofit.entity.CardioLog;
import com.gofit.entity.Roles;
import com.gofit.entity.User;
import com.gofit.exception.ResourceNotFoundException;
import com.gofit.exception.UnauthorisedOperationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CardioLogServiceImpl implements CardioLogService {

    private final CardioLogDAO cardioLogDAO;
    private final ActivityLogService activityLogService;
    private final UserService userService;

    @Override
    public CardioLog getCardioLog(int id) {
        CardioLog log = cardioLogDAO.get(id);

        if (log == null) {
            throw new ResourceNotFoundException("This CardioLog does not exist");
        }

        checkPermission(userService.getCurrentUser(), log);
        return cardioLogDAO.get(id);
    }

    @Override
    public CardioLog saveCardioLog(CardioLog cardioLog) {
        ActivityLog check = activityLogService.get(cardioLog.getActivityLog().getActivityLogId());

        if (check == null) {
            throw new ResourceNotFoundException("This activity log does not exist");
        }

        if (cardioLogDAO.getCardioLogByActivityID(cardioLog.getActivityLog().getActivityLogId()).isPresent()) {
            throw new UnauthorisedOperationException("This CardioLog already exists");
        }

        checkPermission(userService.getCurrentUser(), cardioLog);
        return cardioLogDAO.save(cardioLog);
    }

    @Override
    public CardioLog updateCardioLog(CardioLog cardioLog) {
        if (cardioLogDAO.getCardioLogByActivityID(cardioLog.getActivityLog().getActivityLogId()).isEmpty()) {
            throw new ResourceNotFoundException("This CardioLog does not exist");
        }

        checkPermission(userService.getCurrentUser(), cardioLog);
        return cardioLogDAO.update(cardioLog);
    }

    @Override
    public void deleteCardioLog(int id) {
        CardioLog cardioLog = cardioLogDAO.get(id);
        checkPermission(userService.getCurrentUser(), cardioLog);
        cardioLogDAO.delete(id);
    }

    private boolean checkOwner(User user, CardioLog cardioLog) {
        ActivityLog activityLog = activityLogService.
                getActivityLogAndUser(cardioLog.getActivityLog().getActivityLogId());

        return user.getId() == activityLog.getSession().getUser().getId();
    }

    private boolean hasRole(User user, String role) {
        return user.getRoles().stream().anyMatch(roles -> roles.getRoleName().equals(role));
    }

    private void checkPermission(User user, CardioLog cardioLog) {
        if (!hasRole(user, "ROLE_ADMIN") && !checkOwner(user, cardioLog)) {
            throw new UnauthorisedOperationException("You do not have permission to perform this operation");
        }
    }
}
